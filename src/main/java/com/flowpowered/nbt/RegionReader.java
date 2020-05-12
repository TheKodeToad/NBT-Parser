package com.flowpowered.nbt;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;

/*
 * Class written by natemort at https://github.com/SpongePowered/nbt/issues/8
 */

public class RegionReader {
	private static final int SECTOR_BYTES = 4096;

	public static List<Tag<?>> readFile(File f) {
		try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
			if (raf.length() < SECTOR_BYTES * 3) {
				return Collections.emptyList();
			}
			int maxChunks = ((int) raf.length() / SECTOR_BYTES) - 2;
			int[] chunkLocation = new int[maxChunks];
			int entries = 0;
			for (int i = 0; i < (SECTOR_BYTES / 4); i++) {
				int offset = raf.readInt();
				if (offset != 0) {
					try {
						chunkLocation[entries++] = (offset >> 8) * SECTOR_BYTES;
					}
					catch(ArrayIndexOutOfBoundsException e) {
						continue;
					}
				}
			}
			List<Tag<?>> list = new ArrayList<>();

			for (int i = 0; i < entries; i++) {
				try {
					list.add(readChunk(raf, chunkLocation[i]));
				} 
				catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}

			return list;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

	private static Tag<?> readChunk(RandomAccessFile raf, int location) throws IOException {
		raf.seek(location);
		int length = raf.readInt();
		byte compressionType = raf.readByte();
		byte[] data = new byte[length-1];
		raf.readFully(data);
		return readTag(decompress(compressionType, data));
	}

	private static InputStream decompress(int type, byte[] data) throws IOException {
		switch (type) {
			case 1: return new GZIPInputStream(new ByteArrayInputStream(data));
			case 2: return new InflaterInputStream(new ByteArrayInputStream(data));
			default: throw new IllegalArgumentException("Unknown type");
		}
	}

	private static Tag<?> readTag(InputStream in) throws IOException {
		NBTInputStream input = new NBTInputStream(in, false);
		Tag<?> tag = input.readTag();
		input.close();
		return tag;
	}
}
