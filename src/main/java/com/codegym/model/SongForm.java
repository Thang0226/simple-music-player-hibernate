package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class SongForm {
	private String title;
	private String artist;
	private String gender;
	private MultipartFile file;

	public SongForm() {}

	public SongForm(String title, String artist, String gender, MultipartFile file) {
		this.title = title;
		this.artist = artist;
		this.gender = gender;
		this.file = file;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
