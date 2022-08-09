package com.ilender.micro.service;

import com.ilender.micro.common.NumberUtil;
import com.ilender.micro.common.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Value("${user.random.image.url}")
    private String imageUrl;

    public String createImageUrl(String gender, int userId, boolean customer) {

        String result = imageUrl;
        if (StringUtil.isEqualsIgnoreCase(gender, "male")) {
            result = result + "/" + "men" + "/";
        } else {
            result = result + "/" + "women" + "/";
        }

        String last2Char = StringUtil.getLastChar(String.valueOf(userId), 2);
        int addFactor = 0;
        if (customer) {
            addFactor = 50;
        }
        result = result + NumberUtil.add(last2Char, addFactor);
        result = result + ".jpg";

        return result;
    }

}
