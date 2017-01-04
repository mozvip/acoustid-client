package com.github.mozvip.acoustid;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AcoustIdService {
	
	@GET("/v2/lookup?meta=recordings+releases+releasegroups+tracks+compress+usermeta+sources")
	Call<AcoustIdLookupResults> lookup(@Query("client") String client, @Query("duration") int duration, @Query("fingerprint") String fingerprint);

	@GET("/v2/lookup?meta=releases")
	Call<AcoustIdLookupResults> lookupReleases(@Query("client") String client, @Query("duration") int duration, @Query("fingerprint") String fingerprint);

	@GET("/v2/lookup?meta=recordings")
	Call<AcoustIdLookupResults> lookupRecordings(@Query("client") String client, @Query("duration") int duration, @Query("fingerprint") String fingerprint);

	@GET("/v2/lookup?meta=recordings+releases+releaseids+releasegroups+tracks+compress+usermeta+sources")
	Call<AcoustIdLookupResults> lookup(@Query("client") String client, @Query("trackid") String trackid);

}
