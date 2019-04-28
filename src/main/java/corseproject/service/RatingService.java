package corseproject.service;

import corseproject.domain.Rating;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import corseproject.repos.RatingRepository;
import corseproject.repos.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private TShirtRepository tShirtRepository;

    public void addRate(User authUser, int value, TShirt tShirt){
        Rating rating = ratingRepository.findByTShirtAndAuthor(tShirt, authUser);
        if(rating != null){
            rating.setValue(value);
        }else{
            rating = new Rating();
            rating.setAuthor(authUser);
            rating.setValue(value);
            rating.settShirt(tShirt);
        }
        ratingRepository.save(rating);
        tShirt.setRating(getRating(tShirt));
        tShirtRepository.save(tShirt);
    }
    public double getRating(TShirt tShirt){
        List<Rating> rate = ratingRepository.findByTShirt(tShirt);
        double value = 0;
        for(int i = 0; i < rate.size(); i++){
            value += rate.get(i).getValue();
        }
        value = value / rate.size();
        return value;
    }
    public int getQuantity(TShirt tShirt){
        List<Rating> rate = ratingRepository.findByTShirt(tShirt);
        return rate.size();
    }
}
