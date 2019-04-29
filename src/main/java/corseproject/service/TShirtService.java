package corseproject.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import corseproject.domain.*;
import corseproject.repos.TShirtRepository;
import corseproject.repos.TagRepository;
import corseproject.repos.TopicRepository;
import corseproject.repos.UserRepository;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class TShirtService {
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagRepository tagRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public boolean addTShirt(String username, String svg, String sex, String nameProduct,
                          String topicName, String discription, String tags) throws IOException {
        User user = userRepository.findByUsername(username);
        Topic topic = topicRepository.findByTopicName(topicName);

        String url = addImage(svg);
        TShirt tShirt = new TShirt();
        tShirt.setTags(tagService.getTagsFromString(tags));
        tShirt.setTopic(topic);
        tShirt.setSex(Sex.getSexFromString(sex));
        tShirt.setDescription(discription);
        tShirt.setName(nameProduct);
        tShirt.setUrlShirt(url);
        tShirt.setAuthor(user);
        //tShirt.setId();
        try {
                tShirtRepository.save(tShirt);
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    private String addImage(String svg) throws IOException {

        FileWriter writer = new FileWriter(uploadPath + "/file.png", false);
        writer.append(svg);
        writer.flush();
        String url = addToCloud();
        return url;
    }

    private String addToCloud() throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name","itr",
                "api_key","224226883725776",
                "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
        Map uploadRezult = cloudinary.uploader().upload(uploadPath + "/file.png", ObjectUtils.emptyMap());
        String pathSVG = uploadRezult.get("secure_url").toString();
        String pathPNG = pathSVG.substring(0, pathSVG.length()-3)+"png";
        return pathPNG;
    }

    private List<TShirt> findAll(){
        Iterable<TShirt> iterable = tShirtRepository.findAll();
        ArrayList<TShirt> tShirts = new ArrayList<>();
        iterable.forEach(tShirt -> tShirts.add(tShirt));
        return tShirts;
    }

    public List<TShirt> getNew(){
        ArrayList<TShirt> allTShirts= new ArrayList<>(findAll());
        ArrayList<TShirt> newTShirts = new ArrayList<>();
        for (int i = 1; i <= 5 && (allTShirts.size() - i) >= 0; i++){
            newTShirts.add(allTShirts.get(allTShirts.size() - i));
        }
        return newTShirts;
    }

    public List<TShirt> findWithFilter(String inputSex, String inputtopic, String inputtag){

        Sex sex = Sex.getSexFromString(inputSex);
        Topic topic = topicRepository.findByTopicName(inputtopic);
        Tag tag = tagRepository.findByTagName(inputtag);
        ArrayList<TShirt> tShirts = new ArrayList<>(findAll());
        if(sex != null && topic != null)
        {
            tShirts = new ArrayList<>(tShirtRepository.findBySexAndTopic(sex, topic));
        }else if(sex != null){
            tShirts = new ArrayList<>(tShirtRepository.findBySex(sex));
        }else if (topic != null){
            tShirts = new ArrayList<>(tShirtRepository.findByTopic(topic));
        }
        if(tag != null){

            for(int i = 0; i < tShirts.size(); i++){
                boolean exist = false;
                for (int j = 0; j < tShirts.get(i).getTags().size(); j++){

                    if(tShirts.get(i).getTags().get(j) == tag){
                        exist = true;
                        break;
                    }

                }
                if(!exist){
                    tShirts.remove(i);
                    i--;
                }
            }
        }
        return tShirts;
    }
    public List<TShirt> getPopular() {
        List<TShirt> tShirts = findAll();
        Comparator<TShirt> comparator = Comparator.comparing(obj-> obj.getRating());
        tShirts.sort(comparator);
        ArrayList<TShirt> popularTShirts = new ArrayList<>();
        for(int i = 1; i <= 5 && (tShirts.size() - i) >= 0; i++){
            popularTShirts.add(tShirts.get(tShirts.size() - i));
        }
        return popularTShirts;
    }
}

