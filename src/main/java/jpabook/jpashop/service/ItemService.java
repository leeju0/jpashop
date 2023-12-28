package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    //service는 repository에게 위임만 하는 역할임을 볼 수 있음

    private final ItemRepository itemRepository;

    @Transactional //저장 작업시에는 readOnly x, 롤백 안되어야 되므로
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();

    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
