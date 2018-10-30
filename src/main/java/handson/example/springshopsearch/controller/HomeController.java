package handson.example.springshopsearch.controller;

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
            @RequestParam(name = "param",required = false) Optional<String> param,
            	@RequestParam(name = "keyword", required = false) Optional<String> keyword) {
    				List<Item> list = itemRepository.findAll();
    				if(keyword.isPresent()) {
    					if(param.get().equals("name")) {
    						list = itemRepository.findByNameContainsOrderByIdAsc(keyword.get());
    					}
    					else if(param.get().equals("description")) {
    						list = itemRepository.findByDescriptionContainsOrderByIdAsc(keyword.get());
    					}
    					else if(param.get().equals("nameordescription")) {
    						list = itemRepository.findByNameOrDescriptionContainsOrderByIdAsc(keyword.get() , keyword.get());
    					}
    					
    				}
    				else {
						list = itemRepository.findAll();
					}
        			model.addAttribute("items", list);
        			return "index";
    		}

    @GetMapping("about")
    public String getAbout() {
        return "about";
    }
}
