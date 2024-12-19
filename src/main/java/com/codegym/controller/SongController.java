package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/songs")
public class SongController {
	@Autowired
	private ISongService songService;

	@GetMapping
	public String listSongs(Model model) {
		model.addAttribute("songs", songService.findAll());
		return "list";
	}

	@GetMapping("/create")
	public String createSong(Model model) {
		model.addAttribute("songForm", new SongForm());
		return "create";
	}

	@Value("${file-upload}")
	private String folderPath;

	@PostMapping("/save")
	public String saveSong(@ModelAttribute SongForm songForm, Model model) {
		MultipartFile multipartFile = songForm.getFile();
		String fileName = multipartFile.getOriginalFilename();
		try {
			FileCopyUtils.copy(songForm.getFile().getBytes(), new File(folderPath + fileName));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		Song song = new Song(0, songForm.getTitle(), songForm.getArtist(),
				songForm.getGender(), fileName);
		songService.add(song);
		model.addAttribute("songForm", songForm);
		model.addAttribute("message", "Add new song successfully!");
		return "create";
	}
}
