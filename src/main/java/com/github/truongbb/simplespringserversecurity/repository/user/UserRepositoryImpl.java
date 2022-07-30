package com.github.truongbb.simplespringserversecurity.repository.user;

import com.github.truongbb.simplespringserversecurity.dto.UserDto;
import com.github.truongbb.simplespringserversecurity.entity.User;
import com.github.truongbb.simplespringserversecurity.repository.BaseRepository;
import com.github.truongbb.simplespringserversecurity.web.vm.UserVm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl extends BaseRepository implements UserRepository {

  @Override
  public List<UserDto> findByUsernameOrEmailOrPhone(String searchField, String value) {
//        List<UserDto> userDtos = null;
//
//        try {
//            StringBuilder sql = new StringBuilder(SQLBuilder.getSqlFromFile(SQLBuilder.SQL_MODULE_USER, "find_by_username_mail_phone"));
//            Map<String, Object> parameters = new HashMap<>();
//            if (Constants.UserFindField.EMAIL.equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(value)) {
//                sql.append(" and email = :p_email");
//                parameters.put("p_email", value);
//            } else if (Constants.UserFindField.USERNAME.equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(value)) {
//                sql.append(" and username = :p_username");
//                parameters.put("p_username", value);
//            } else if (Constants.UserFindField.PHONE.equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(value)) {
//                sql.append(" and phone_number = :p_phone");
//                parameters.put("p_phone", value);
//            }
//            userDtos = getNamedParameterJdbcTemplate().query(sql.toString(), parameters, new BeanPropertyRowMapper<>(UserDto.class));
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return userDtos;
    return null;
  }

  @Override
  public boolean updateUserEntity(User usersEntity) {
    User merge = getEntityManager().merge(usersEntity);
    return merge != null;
  }

  @Override
  public List<User> search(UserVm userVm) {
    return null;
  }
}
