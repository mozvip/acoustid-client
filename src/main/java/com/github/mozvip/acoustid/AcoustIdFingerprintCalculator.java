package com.github.mozvip.acoustid;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.MatchResult;

import org.apache.commons.lang3.StringUtils;

public class AcoustIdFingerprintCalculator {
	
	private Path pathToFpCalc;
	
	public static final class Builder {
		
		private Path pathToFpCalc;
		
		public Builder pathToFpCalc( Path pathToFpCalc ) {
			this.pathToFpCalc = pathToFpCalc;
			return this;
		}

		public AcoustIdFingerprintCalculator build() {
			return new AcoustIdFingerprintCalculator(pathToFpCalc);
		}
		
	}
	
	public static Builder Builder() {
		return new Builder();
	}	
	
	private AcoustIdFingerprintCalculator( Path pathToFpCalc ) {
		this.pathToFpCalc = pathToFpCalc;
	}
	
	public AcoustIdFingerprint calculate( Path musicFile ) throws IOException {
		if (!Files.isExecutable( pathToFpCalc)) {
			throw new IOException( String.format("%s is not executable", pathToFpCalc.toString()));
		}
		
		if (!Files.isReadable( musicFile )) {
			throw new IOException( String.format("%s is not readable", musicFile.toString()));
		}

		String fpcalcPath = pathToFpCalc.toAbsolutePath().normalize().toString();

		ProcessBuilder fpcalcPb = new ProcessBuilder(fpcalcPath, musicFile.toAbsolutePath().normalize().toString());
		fpcalcPb.directory( musicFile.getParent().toFile() );
		Process p = fpcalcPb.start();

		AcoustIdFingerprint fingerprint = new AcoustIdFingerprint();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				
				if (line == null || StringUtils.isBlank( line )) {
					continue;
				}
				
				try (Scanner s = new Scanner( line )) {
					s.findInLine("(\\w+)=(.+)");
					MatchResult result = s.match();
					
					String key = result.group(1);
					String value = result.group(2);
					
					switch (key) {
					case "FILE":
						fingerprint.setFile( value );
						break;
					case "DURATION":
						fingerprint.setDuration( Integer.parseInt( value ) );						
						break;
					case "FINGERPRINT":
						fingerprint.setFingerprint( value );
						break;

					default:
						break;
					}
				}
			}
		}

		return fingerprint;
	}

}
