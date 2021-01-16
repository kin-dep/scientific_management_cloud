package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.PublicationPlace;
import com.sicnu.system_management.util.Result;

public interface PublicationPlaceService {
    Result addPublicationPlace(String pp_name);
    Result delPublicationPlace(Integer pp_id);
    Result findAllPublicationPlace();
    Result updatePublicationPlace(PublicationPlace publicationPlace);
}
