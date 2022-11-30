package com.example.springblog3;

import com.example.springblog3.models.Ad;
import com.example.springblog3.models.User;
import com.example.springblog3.repositories.AdRepository;
import com.example.springblog3.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdService")
public class AdService {

    private final AdRepository adsDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public AdService(AdRepository adsDao, UserRepository userDao, EmailService emailService) {
        this.adsDao = adsDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    public List<Ad> getAds(){
        List<Ad> ads = adsDao.findAll();
        return ads;
    }

    public void createAd(Ad ad){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.getReferenceById(1L);
        ad.setUser(currentUser);
        adsDao.save(ad);
        String subject = "Thank you for making an ad!";
        String description = "Here is what you submitted: " + ad.getDescription();
        emailService.prepareAndSend(ad, subject, description);
    }

    public Ad findById(long id) {
        return adsDao.getReferenceById(id);
    }

    public void deleteAd(Ad ad) {
        adsDao.delete(ad);
    }

    public void editAd(Ad ad) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ad.setUser(loggedInUser);
        adsDao.save(ad);
    }
}