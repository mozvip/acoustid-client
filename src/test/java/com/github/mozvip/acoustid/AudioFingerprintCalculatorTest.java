package com.github.mozvip.acoustid;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.mozvip.acoustid.AcoustIdFingerprint;
import com.github.mozvip.acoustid.AcoustIdFingerprintCalculator;

public class AudioFingerprintCalculatorTest {

	@Test
	public void testCalculate() throws IOException {
		
		AcoustIdFingerprintCalculator fingerprinter = AcoustIdFingerprintCalculator.Builder()
				.pathToFpCalc( Paths.get("d:\\apps\\fpcalc.exe"))
				.build();
		AcoustIdFingerprint fingerprint = fingerprinter.calculate( Paths.get("\\\\DLINK-4T\\Volume_2\\music-albums\\Foo Fighters\\Greatest Hits\\01 - All My Life.mp3"));
		
		System.out.println( fingerprint.getFingerprint() );
	}

}
