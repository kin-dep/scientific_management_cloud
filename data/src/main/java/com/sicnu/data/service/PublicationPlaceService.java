package com.sicnu.data.service;

import com.sicnu.data.PublicationPlace;
import com.sicnu.data.Result;

public interface PublicationPlaceService {
    Result addPublicationPlace(String pp_name);
    Result delPublicationPlace(Integer pp_id);
    Result findAllPublicationPlace();
    Result updatePublicationPlace(PublicationPlace publicationPlace);
}
