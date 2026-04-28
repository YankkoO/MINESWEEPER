package uo.mp.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil extends BaseFileUtil {

	@Override
	protected BufferedReader createBufferedReader(String filename) throws FileNotFoundException {
		return new BufferedReader(new FileReader(filename));

	}

	@Override
	protected BufferedWriter createBufferedWriter(String filename) {
		try {
			return new BufferedWriter(new FileWriter(filename));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

}
