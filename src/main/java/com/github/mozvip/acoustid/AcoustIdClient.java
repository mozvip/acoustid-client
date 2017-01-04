package com.github.mozvip.acoustid;


import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AcoustIdClient {

	private String apiKey;
	private AcoustIdService service;
	
	public static final class Builder {
		
		private String apiKey;
		
		public Builder apiKey( String apiKey ) {
			this.apiKey = apiKey;
			return this;
		}

		public AcoustIdClient build() {
			return new AcoustIdClient(apiKey);
		}
		
	}
	
	public static Builder Builder() {
		return new Builder();
	}

	private AcoustIdClient(String apiKey) {
		this.apiKey = apiKey;

		Retrofit restAdapter = new Retrofit.Builder()
				.baseUrl("http://api.acoustid.org")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
		service = restAdapter.create(AcoustIdService.class);
	}

	public AcoustIdLookupResults lookup(int duration, String fingerprint) throws IOException {
		return service.lookup(apiKey, duration, fingerprint).execute().body();
	}

	public AcoustIdLookupResults lookupReleases(int duration, String fingerprint) throws IOException {
		return service.lookupReleases(apiKey, duration, fingerprint).execute().body();
	}

	public AcoustIdLookupResults lookup(String trackid) throws IOException {
		return service.lookup(apiKey, trackid).execute().body();
	}

	public AcoustIdLookupResults lookupRecordings(int duration, String fingerprint) throws IOException {
		return service.lookupRecordings(apiKey, duration, fingerprint).execute().body();
	}

}
