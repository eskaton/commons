/*
 *  Copyright (c) 2013, Adrian Moser
 *  All rights reserved.
 * 
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  * Neither the name of the author nor the
 *  names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 * 
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL AUTHOR BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ch.eskaton.commons;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class HexDump {

	private static final int BYTES_PER_WORD = 4;

	private static final int BYTES_PER_LINE = 4 * BYTES_PER_WORD;

	public static String toHexString(byte buf[]) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < buf.length; i++) {
			sb.append(String.format("%02x", buf[i]));
		}

		return sb.toString();
	}

	public static String dumpToString(byte buf[]) throws IOException {
		StringWriter sw = new StringWriter();
		dump(sw, buf);
		return sw.toString();
	}

	public static String dumpToString(byte buf[], int offset, int len)
			throws IOException {
		StringWriter sw = new StringWriter();
		dump(sw, buf, offset, len);
		return sw.toString();
	}

	public static void dump(Writer w, byte buf[]) throws IOException {
		dump(w, buf, 0, buf.length);
	}

	public static void dump(Writer w, byte buf[], int offset, int len)
			throws IOException {
		int lines = len / BYTES_PER_LINE;
		int last_len = len % BYTES_PER_LINE;
		int addr_len = (int) Math.floor(Math.log(len)
				/ Math.log(BYTES_PER_LINE) + 1);
		int addr = offset;

		for (int i = 0; i < lines; i++) {
			dumpLine(w, buf, addr, addr_len, BYTES_PER_LINE);
			addr += BYTES_PER_LINE;
		}

		if (last_len > 0) {
			dumpLine(w, buf, addr, addr_len, last_len);
		}

		w.flush();
	}

	private static void dumpLine(Writer w, byte[] buf, int addr, int addr_len,
			int len) throws IOException {
		int words = len / BYTES_PER_WORD;
		int last_word = len % BYTES_PER_WORD;
		int pos = addr;
		int line_pos = 0;

		String format = String.format("%%0%dx", addr_len);

		w.append(String.format(format, addr)).append(" ");

		for (int i = 0; i < words; i++) {
			line_pos += dumpWord(w, buf, pos, BYTES_PER_WORD);
			pos += BYTES_PER_WORD;
		}

		if (last_word > 0) {
			line_pos += dumpWord(w, buf, pos, last_word);
		}

		format = String.format("%%%ds", BYTES_PER_LINE * 2 + BYTES_PER_LINE
				/ BYTES_PER_WORD - line_pos + 2);

		w.append(String.format(format, "| "));

		for (int i = 0; i < len; i++) {
			Character c = Character.valueOf((char) buf[addr + i]);
			if (Character.isISOControl(c)) {
				w.append(".");
			} else {
				w.append(Character.valueOf((char) buf[addr + i]));
			}
		}

		w.append("\n");
	}

	private static int dumpWord(Writer w, byte[] buf, int pos, int bytesPerWord)
			throws IOException {
		int i;
		int word_len = 0;

		for (i = 0; i < bytesPerWord; i++) {
			w.append(String.format("%02x", buf[pos + i]));
			word_len += 2;
		}

		w.append(" ");

		return word_len + 1;
	}

}
