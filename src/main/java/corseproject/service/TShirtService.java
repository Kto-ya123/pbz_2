package corseproject.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import corseproject.domain.Sex;
import corseproject.domain.TShirt;
import corseproject.domain.Topic;
import corseproject.domain.User;
import corseproject.repos.TShirtRepository;
import corseproject.repos.TopicRepository;
import corseproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Service
public class TShirtService {
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public boolean addTShirt(String username, String svg, String sex, String nameProduct,
                          String topicName, String discription, String tagsString) throws IOException {
        User user = userRepository.findByUsername(username);
        Topic topic = topicRepository.findByTopicName(topicName);
        String url = addImage(svg);
        TShirt tShirt = new TShirt();
        tShirt.setTopic(topic);
        tShirt.setSex(Sex.getSexFromString(sex));
        tShirt.setDescription(discription);
        tShirt.setName(nameProduct);
        tShirt.setUrlShirt(url);
        tShirt.setAuthor(user);

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
        String url = addToCloud(uploadPath+"/file.png");
        return url;
    }

    private String addToCloud(String path) throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name","itr",
                "api_key","224226883725776",
                "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
        Map uploadRezult = cloudinary.uploader().upload(uploadPath + "/file.png", ObjectUtils.emptyMap());
        String pathSVG = uploadRezult.get("secure_url").toString();
        String pathPNG = pathSVG.substring(0, pathSVG.length()-3)+"png";
        return pathPNG;
    }
}

