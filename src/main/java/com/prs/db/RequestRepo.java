package com.prs.db;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.LineItem;
import com.prs.business.Request;

public interface RequestRepo extends JpaRepository<Request, Integer>{


}

