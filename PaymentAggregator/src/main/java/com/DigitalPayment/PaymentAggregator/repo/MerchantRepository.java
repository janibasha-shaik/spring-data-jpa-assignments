package com.DigitalPayment.PaymentAggregator.repo;

import com.DigitalPayment.PaymentAggregator.entity.Merchant;
import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {

}
