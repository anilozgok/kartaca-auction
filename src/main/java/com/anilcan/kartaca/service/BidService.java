package com.anilcan.kartaca.service;

import com.anilcan.kartaca.exception.InvalidBidPriceException;
import com.anilcan.kartaca.exception.ItemNotFoundException;
import com.anilcan.kartaca.exception.UserNotFoundException;
import com.anilcan.kartaca.model.dto.BidDTO;
import com.anilcan.kartaca.model.entity.Bid;
import com.anilcan.kartaca.repository.BidRepository;
import com.anilcan.kartaca.repository.ItemRepository;
import com.anilcan.kartaca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    public BidDTO offerBid(BidDTO bidDTO) {
        log.info("offerBid processing.");
        var item = itemRepository.findById(bidDTO.bidItemId()).orElseThrow(ItemNotFoundException::new);

        var bidder = userRepository.findById(bidDTO.bidderId()).orElseThrow(UserNotFoundException::new);

        if (bidDTO.bidPrice() <= 0 || bidDTO.bidPrice() <= item.getInitialPrice()) throw new InvalidBidPriceException();

        var bidToSave = Bid.builder()
                           .bidItemId(item)
                           .bidderId(bidder)
                           .bidPrice(bidDTO.bidPrice())
                           .build();

        var savedBid = bidRepository.save(bidToSave);

        return new BidDTO(savedBid.getBidderId().getId(), savedBid.getBidItemId().getId(), savedBid.getBidPrice());
    }

    public List<Bid> findBidsByBidItemId(Long id){
        return bidRepository.findBidsByBidItemId(id);
    }

}
