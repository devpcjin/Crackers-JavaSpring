package com.cracker.service;

import com.cracker.controller.PlaceController;
import com.cracker.domain.Place;
import com.cracker.dto.PlaceCreateRequestDto;
import com.cracker.dto.PlaceListRequestDto;
import com.cracker.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public Long save(PlaceCreateRequestDto placeCreateRequestDto) {
        Place place = Place.builder()
                .name(placeCreateRequestDto.getName())
                .addr(placeCreateRequestDto.getAddr())
                .addrRoad(placeCreateRequestDto.getAddrRoad())
                .coordX(placeCreateRequestDto.getCoordX())
                .coordY(placeCreateRequestDto.getCoordY())
                .phoneNum(placeCreateRequestDto.getPhoneNum())
                .cate(placeCreateRequestDto.getCate())
        .build();

        return placeRepository.save(place).getId();
    }

    @Transactional
    public List<Place> placeList() {
        return placeRepository.findAll();
    }

//    public PlaceListRequestDto findPlace(Long id) {
//        Place place = placeRepository.findById(id).orElseThrow(
//                () -> new NullPointerException("no list found")
//        );

//        return PlaceListRequestDto.builder()
//                .name(place.getName())
//                .addr(place.getAddr())
//                .addrRoad(place.getAddrRoad())
//                .coordX(place.getCoordX())
//                .coordY(place.getCoordY())
//                .phoneNum(place.getPhoneNum())
//                .cate(place.getCate())
//                .build();
//    }

    @Transactional
    public Long deletePlace(Long id) {
        placeRepository.deleteById(id);
        return id;
    }
}
