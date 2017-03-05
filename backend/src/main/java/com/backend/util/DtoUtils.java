package com.backend.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.backend.dto.user.UserDto;
import com.backend.entity.user.User;

/**
 * 数据传输对象打包工具类
 * <p>
 * 将实体类时间打包成对应的数据传输对象。
 * </p>
 * 
 * @author 隔壁老王
 * 
 * @param <CDTO>
 * @param <CEntity>
 */
public class DtoUtils<CDTO, CEntity> {
	private static final Logger logger = LoggerFactory
			.getLogger(DtoUtils.class);
	public static DtoUtils<UserDto, User> userDtoUtil = new DtoUtils<UserDto, User>(
			UserDto.class, User.class);
	private Class<?> dtoClass;
	private Class<?> entityClass;

	public DtoUtils(Class<?> dtoClass, Class<?> entityClass) {
		super();
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}

	@SuppressWarnings("unchecked")
	private CDTO newDtoInstance(CEntity entity) {
		try {
			return (CDTO) dtoClass.getConstructor(entityClass).newInstance(
					entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public List<CDTO> toDTO(List<CEntity> listSource) {
		List<CDTO> result = new ArrayList<CDTO>();
		for (CEntity entity : listSource) {
			result.add(newDtoInstance(entity));
		}
		return result;
	}

	public Page<CDTO> toDTO(Page<CEntity> pageSource, Pageable pageRequest) {
		return new PageImpl<CDTO>(toDTO(pageSource.getContent()), pageRequest,
				pageSource.getTotalElements());
	}

	@SuppressWarnings("unchecked")
	public List<CEntity> toEntity(List<CDTO> listSource) {
		List<CEntity> result = new ArrayList<CEntity>();
		for (CDTO dto : listSource) {
			try {
				Method method = dto.getClass().getMethod("toEntity");
				result.add((CEntity) method.invoke(dto));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}

}
