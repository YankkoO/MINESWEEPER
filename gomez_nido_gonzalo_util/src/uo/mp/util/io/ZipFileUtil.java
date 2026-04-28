package uo.mp.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * A utility class to read/write text lines from/to a compressed text file
 * (.txt.gz)
 */
public class ZipFileUtil extends BaseFileUtil{
	
	@Override
	protected BufferedReader createBufferedReader(String fileName) throws FileNotFoundException{
    	try {
			return new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fileName))));
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw new UncheckedIOException( e );
		}
    }

	@Override
	protected BufferedWriter createBufferedWriter(String filename) {
		try {
			return new BufferedWriter(
					new OutputStreamWriter(
					new GZIPOutputStream(
					new FileOutputStream(filename))));
		} catch (IOException e) {
			throw new UncheckedIOException( e );
		}
	}
}
