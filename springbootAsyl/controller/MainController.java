package kz.AsylbekSpring.springbootAsyl.controller;


import kz.AsylbekSpring.springbootAsyl.db.Item;
import kz.AsylbekSpring.springbootAsyl.db.Library;
import kz.AsylbekSpring.springbootAsyl.repository.BookRepository;
import kz.AsylbekSpring.springbootAsyl.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller

public class MainController {

    @Autowired

    private ItemRepository itemRepository;

    @Autowired

    private BookRepository bookRepository;

    @GetMapping(value = "/")
    public String addItem(Model model){

        List<Library> book = bookRepository.findAll();
        model.addAttribute(" book",book);
        List<Item> items = itemRepository.findAll();
        model.addAttribute("additem",items);
        return "additem";

    }

    @GetMapping(value = "/additem")

    public String indexPage(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("book",items);
        return "index";

    }

    @GetMapping(value = "/login")
    public String login(Model model){

        return "login";

    }

    @GetMapping(value = "/profile")

    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){

        return "profile";

    }

    @GetMapping(value = "/adminpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String admin(Model model){

        return "adminpanel";

    }

    @GetMapping(value = "/moderatorpanel")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public String moderator(Model model){

        return "moderatopanel";

    }

    @GetMapping(value = "/list")
    public String listPage(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("list",items);

        return "list";

    }

    @GetMapping(value = "/bestseller")

    public String bestseller(Model model){
        List<Library> book = bookRepository.findAll();
        model.addAttribute("best",book);

        return "bestseller";

    }

    @PostMapping(value = "/additem")

    public String addItemPage(@RequestParam(name = "name") String name,
                              @RequestParam(name = "author")String author,
                              @RequestParam(name = "publishing")String publishing,
                              @RequestParam(name = "description")String description,
                              @RequestParam(name = "numberOfBook")int numberOfBook,
                              @RequestParam(name = "genre")String genre){

        Item item = new Item();
        item.setName(name);
        item.setAuthor(author);
        item.setPublishing(publishing);
        item.setDescription(description);
        item.setNumberOfBook(numberOfBook);
        item.setGenre(genre);
        itemRepository.save(item);

        return "redirect:/list";

    }

    @PostMapping(value = "/bestseller")

    public String bestseller(@RequestParam(name = "name") String name,
                             @RequestParam(name = "author")String author,
                             @RequestParam(name = "genre") String genre){

        Library books = new Library();
        books.setName(name);
        books.setAuthor(author);
        books.setGenre(genre);
        bookRepository.save(books);
        return "redirect:/bestseller";

    }

    @GetMapping(value = "/details/{itemId}")

    public String itemDetails(

            @PathVariable(name = "itemId")Long id , Model model){

        Item item = itemRepository.findById(id).orElse(null);
        model.addAttribute("home",item);
        return "details";

    }

    @GetMapping(value = "/bdetails/{b-Id}")

    public String bDetails(

            @PathVariable(name = "b-Id")Long id , Model model){
        Library book = bookRepository.findById(id).orElse(null);
        model.addAttribute("rrr",book);

        return "bdetails";

    }

    @GetMapping(value = "/information/{itemId}")

    public String information(

            @PathVariable(name = "itemId")Long id , Model model){
        Item item = itemRepository.findById(id).orElse(null);
        model.addAttribute("info",item);

        return "information";

    }

    @PostMapping(value = "/saveitem")

    public String saveItem(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name ="author")String author,
                           @RequestParam(name = "description")String description,
                           @RequestParam(name = "numberOfBook")int numberOfBook,
                           @RequestParam(name = "genre")String genre){

        Item item = itemRepository.findById(id).orElse(null);

        if(item!= null){
            item.setName(name);
            item.setAuthor(author);
            item.setDescription(description);
            item.setNumberOfBook(numberOfBook);
            item.setGenre(genre);
            itemRepository.save(item);

            return "redirect:/details/"+id;

        }

        return "redirect:/list";

    }

    @PostMapping(value = "/deleteitem")

    public String deleteItem(@RequestParam(name = "id")Long id ){
        Item item = itemRepository.findById(id).orElse(null);
        if(item!=null){
            itemRepository.delete(item);
        }
        return "redirect:/";

    }

}
