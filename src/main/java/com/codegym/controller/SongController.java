package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import com.codegym.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
//	@PostMapping("/save")
//	public ModelAndView saveProduct(@ModelAttribute ProductForm productForm) {
//		MultipartFile multipartFile = productForm.getImage();
//		String fileName = multipartFile.getOriginalFilename();
//		try {
//			FileCopyUtils.copy(productForm.getImage().getBytes(), new File(folderPath + fileName));
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			System.out.println(ex.getMessage());
//		}
//		Product product = new Product(productForm.getId(), productForm.getName(),
//				productForm.getDescription(), fileName);
//		productService.add(product);
//		ModelAndView modelAndView = new ModelAndView("create");
//		modelAndView.addObject("productForm", productForm);
//		modelAndView.addObject("message", "Created new product successfully!");
//		return modelAndView;
}
