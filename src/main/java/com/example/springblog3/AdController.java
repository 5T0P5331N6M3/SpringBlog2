package com.example.springblog3;

import com.example.springblog3.models.Ad;
import com.example.springblog3.repositories.UserRepository;
import com.example.springblog3.services.AdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class AdController {

    private final AdService adService;
    private final UserRepository userDao;

    public AdController(AdService adService, UserRepository userDao) {
        this.adService = adService;
        this.userDao = userDao;
    }

    @GetMapping("/ads")
    public String allAds(Model model) {
        List<Ad> ads = adService.getAds();
        // Other methods to note:
        // .save() - insert new record, or update a record.
        // .delete() - delete a record
        // .findById() - find record by id
        model.addAttribute("ads", ads);
        return "ads/ads";

    }

    @GetMapping("/ads/{id}")
    public String showAd(@PathVariable long id, Model model) {
        Ad ad = adService.findById(id);
        model.addAttribute("ad", ad);
        return "ads/show";
    }


    @GetMapping("/ads/create")
    public String showCreateForm(Model model) {
        model.addAttribute("ad", new Ad());
        return "ads/adCreate";
    }

    @PostMapping("/ads/create")
    public String create(@ModelAttribute Ad ad){
        adService.createAd(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("adToEdit", adService.findById(id));
        return "ads/editAd";
    }

    @PostMapping("/ads/{id}/edit")
    public String submitAdChanges(@ModelAttribute Ad ad) {
        adService.editAd(ad);
        return "redirect:/ads";
    }


    @PostMapping("/ads/{id}/delete")
    public String deleteAd(@PathVariable long id){
        adService.deleteAd(adService.findById(id));
        return "redirect:/ads";
    }

}