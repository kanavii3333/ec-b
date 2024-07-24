package com.fullness.ec.repository;

import com.fullness.ec.entity.Account;

public interface AccountRepository {
    Account selectByUsername(String username);
}
