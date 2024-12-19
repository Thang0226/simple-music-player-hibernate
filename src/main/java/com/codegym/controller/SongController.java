package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
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
	public String showCreateForm(Model model) {
		model.addAttribute("songForm", new SongForm());
		return "create";
	}

	@Value("${file-upload}")
	private String folderPath;

	@PostMapping("/save")
	public String saveSong(SongForm songForm, Model model) {
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

	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("song", songService.findById(id));
		return "update";
	}
	@PostMapping("/update")
	public String updateSong(Song song, Model model) {
		songService.update(song);
		model.addAttribute("message", "Update song successfully!");
		return "update";
	}

	@GetMapping("/{id}/delete")
	public String showDeleteForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("song", songService.findById(id));
		return "delete";
	}
	@PostMapping("/delete")
	public String deleteSong(Song song, Model model) {
		songService.remove(song.getId());
		return "redirect:/songs";
	}
}
