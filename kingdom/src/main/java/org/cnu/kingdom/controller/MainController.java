package org.cnu.kingdom.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {
	public String main() {
		return "main";
	}
}
