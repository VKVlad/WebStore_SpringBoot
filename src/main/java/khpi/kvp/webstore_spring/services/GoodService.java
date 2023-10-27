package khpi.kvp.webstore_spring.services;

import khpi.kvp.webstore_spring.models.Good;
import khpi.kvp.webstore_spring.repositories.GoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoodService {
    private final GoodRepository goodRepository;

    public List<Good> goodList(String nazva) {
        if (nazva != null && !nazva.trim().isEmpty()) {
            return goodRepository.findByNazva(nazva);
        } else {
            return goodRepository.findAll();
        }
    }

    public Good getGoodById(Long id) {
        return goodRepository.findById(id).orElse(null);
    }

    public void saveGood(Good good) {
        log.info("Saving new {}", good);
        goodRepository.save(good);
    }

    public void deleteGood(Long id) {
        goodRepository.deleteById(id);
    }
}
