package com.github.mozvip.acoustid.model;

import java.util.List;

public class ReleaseGroup {

	private String type;
	private String id;
	private String title;
	private List<Release> releases;
	private List<Artist> artists;
	private List<String> secondarytypes;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Release> getReleases() {
		return releases;
	}

	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public List<String> getSecondarytypes() {
		return secondarytypes;
	}

	public void setSecondarytypes(List<String> secondarytypes) {
		this.secondarytypes = secondarytypes;
	}

}
