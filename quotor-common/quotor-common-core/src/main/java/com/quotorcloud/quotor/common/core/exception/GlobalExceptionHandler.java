/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.quotorcloud.quotor.common.core.exception;

import com.quotorcloud.quotor.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author lengleng
 * @date 2019/2/1
 * 全局的的异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R exception(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);
		return R.failed(e);
	}

	/**
	 * 捕捉UnauthorizedException
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MyException.class)
	@ResponseBody
	public R handleUnauthorized(MyException e) {
		log.error("自定义异常信息 ex={}", e.getMessage());
		return R.failed(e.getCode(), e.getMessage());
	}

	/**
	 * validation Exception
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		log.warn(fieldErrors.get(0).getDefaultMessage());
		return R.failed(fieldErrors.get(0).getDefaultMessage());
	}

}
