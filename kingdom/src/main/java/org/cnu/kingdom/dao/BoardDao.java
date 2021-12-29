package org.cnu.kingdom.dao;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

public class BoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
}
