package handson.example.springshopsearch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	ItemRepository itemRepository;

	@GetMapping
	public String index(
			Model model,
			@RequestParam(name = "param", required = false) Optional<String> param,
			@RequestParam(name = "keyword", required = false) Optional<String> keyword) {
		//Itemモデルのリストを作成している
		//listの中身はitemRepositoryの中からすべてを検索した結果
		List<Item> list = itemRepository.findAll();
		if (keyword.isPresent() && keyword.isPresent()) {
			switch (param.get()) {
			case "name":
				list = itemRepository.findByNameContainsOrderByIdAsc(keyword.get());
				break;

			case "description":
				list = itemRepository.findByDescriptionContainsOrderByIdAsc(keyword.get());
				break;

			case "nameordescription":
				list = itemRepository.findByNameOrDescriptionContainsOrderByIdAsc(keyword.get(), keyword.get());
				break;

			default:
				list = itemRepository.findAll();
			}
		} else {
			list = new ArrayList<>();
		}
		model.addAttribute("items", list);
		return "index";
	}

	@GetMapping("about")
	public String getAbout() {
		return "about";
	}

	@GetMapping("/hello")
	public String hello(@RequestParam("name") String name) {
		return "";
	}

}

