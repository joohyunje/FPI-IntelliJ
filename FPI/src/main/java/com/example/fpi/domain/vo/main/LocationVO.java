package com.example.fpi.domain.vo.main;

import com.example.fpi.domain.dto.main.CategoryListDTO;
import com.example.fpi.domain.dto.main.LocationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class LocationVO {
    private Long locationId;
    private String region;
    private String city;

    @Builder
    public LocationVO(Long locationId, String region, String city) {
        this.locationId = locationId;
        this.region = region;
        this.city = city;
    }

    public static LocationVO toEntity(LocationDTO locationDTO){
        return LocationVO.builder()
                .locationId(locationDTO.getLocationId())
                .region(locationDTO.getRegion())
                .city(locationDTO.getCity())
                .build();
    }

}
