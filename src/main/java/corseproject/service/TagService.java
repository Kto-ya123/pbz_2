package corseproject.service;

import corseproject.domain.Tag;
import corseproject.repos.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.ListView;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTagsFromString(String tags) {
        if (tags.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Tag> outTags = new ArrayList<>();
        while (true) {
            int index;
            for (index = 0; index < tags.length() && tags.charAt(index) != ','; index++) {
            }
            String tagName = tags.substring(0, index);

            Tag tag = new Tag();
            tag.setTagName(tagName);
            outTags.add(saveTag(tag));
            if(tags.length() <= index){
                break;
            }else{
                tags = tags.substring(index + 1);
            }

        }
        return outTags;
    }

    public Tag saveTag(Tag tag){
        Tag tagFromBd = tagRepository.findByTagName(tag.getTagName());
        if(tagFromBd == null){
            tagRepository.save(tag);
            return tagRepository.findByTagName(tag.getTagName());
        }
        return tagFromBd;
    }
}
