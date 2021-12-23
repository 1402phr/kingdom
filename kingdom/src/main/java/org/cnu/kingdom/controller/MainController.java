package org.cnu.kingdom.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	@RequestMapping({"/", "/main.cnu"})
	public String getView() {
		return "main";
	}
}
