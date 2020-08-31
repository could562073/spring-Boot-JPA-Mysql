package com.rex.demo.service.Impl;

import com.rex.demo.entity.UserEntity;
import com.rex.demo.model.request.UserRequest;
import com.rex.demo.model.response.UserResponse;
import com.rex.demo.repository.UserRepository;
import com.rex.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 使用者實作類別 CRUD Example
 *
 * @author rex
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * @Transactional 註解的屬性資訊說明:
     * name：當在配置檔案中有多個 TransactionManager , 可以用該屬性指定選擇哪個事務管理器。
     * propagation ：事務的傳播行為，預設值為 REQUIRED。
     * isolation：事務的隔離度，預設值採用 DEFAULT。
     * timeout：事務的超時時間，預設值為-1。如果超過該時間限制但事務還沒有完成，則自動回滾事務。
     * read-only：指定事務是否為只讀事務，預設值為 false；為了忽略那些不需要事務的方法，比如讀取資料，可以設定 read-only 為 true。
     * rollback-for：用於指定能夠觸發事務回滾的異常型別，如果有多個異常型別需要指定，各型別之間可以通過逗號分隔。
     * no-rollback- for：丟擲 no-rollback-for 指定的異常型別，不回滾事務。
     */


    /**
     * 新增一個使用者
     *
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResponseEntity<Boolean> add(UserRequest userRequest) {
        logger.info("UserRequestArgs[ account:{} , password:{} , username:{}, address:{}, phoneNumber:{} ]",
                userRequest.getAccount(), userRequest.getPassword(), userRequest.getUsername(), userRequest.getAddress(), userRequest.getPhoneNumber());

        if (userRepository.existsByAccount(userRequest.getAccount())) {
            throw new IllegalArgumentException("Account exists");
        }

        UserEntity userEntity = new UserEntity.Builder()
                .setAccount(userRequest.getAccount())
                .setPassword(userRequest.getPassword())
                .setUsername(userRequest.getUsername())
                .setAddress(userRequest.getAddress())
                .setPhoneNumber(userRequest.getPhoneNumber())
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(true);
    }

    /**
     * 修改一個使用者
     *
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    @Override
    @Transactional
    public ResponseEntity<Boolean> update(UserRequest userRequest) {
        logger.info("UserRequestArgs[ account:{} , password:{} , username:{}, address:{}, phoneNumber:{} ]",
                userRequest.getAccount(), userRequest.getPassword(), userRequest.getUsername(), userRequest.getAddress(), userRequest.getPhoneNumber());

        UserEntity userEntity = userRepository.findByAccount(userRequest.getAccount());
        if (userEntity == null) {
            throw new IllegalArgumentException("Account not found");
        }

        userEntity.setAccount(userRequest.getAccount());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setAddress(userRequest.getAddress());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());

        userRepository.save(userEntity);

        return ResponseEntity.ok(true);
    }


    /**
     * 刪除一個使用者
     *
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED, readOnly = false)
    public ResponseEntity<Boolean> remove(UserRequest userRequest) {
        logger.info("UserRequestArgs[ account:{} ]", userRequest.getAccount());
        UserEntity userEntity = userRepository.findByAccount(userRequest.getAccount());
        if (userEntity == null) {
            throw new IllegalArgumentException("Account does not exist");
        }
        userRepository.delete(userEntity);
        return ResponseEntity.ok(true);
    }

    /**
     * 取得一個使用者
     *
     * @param account 使用者account
     * @return UserResponse 使用者資料vo
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ResponseEntity<UserResponse> getOne(String account) {
        if (!userRepository.existsByAccount(account)) {
            throw new IllegalArgumentException("Account does not exist");
        }

        UserEntity userEntity = userRepository.findByAccount(account);
        UserResponse userResponse = UserResponse.valueOf(userEntity);

        return ResponseEntity.ok(userResponse);
    }

    /**
     * 取得使用者列表
     *
     * @return List<UserResponse> 使用者資料vo列表
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ResponseEntity<List<UserResponse>> list() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        userEntityList.forEach(object -> userResponseList.add(UserResponse.valueOf(object)));

        return ResponseEntity.ok(userResponseList);
    }
}
