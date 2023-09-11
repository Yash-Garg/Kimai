import 'dart:developer';

import 'package:dio/dio.dart';

sealed class ApiResult<dT> {}

class ApiSuccess<T> extends ApiResult<T> {
  final T data;

  ApiSuccess(this.data);
}

class ApiError<T> extends ApiResult<T> {
  final int code;
  final String? message;

  ApiError({
    required this.code,
    required this.message,
  });
}

Future<ApiResult<T>> handleApi<T>(
  Future<Response<T>> Function() execute,
) async {
  try {
    final response = await execute();

    log('Response: ${response.data}');

    // ignore: null_check_on_nullable_type_parameter
    return ApiSuccess(response.data!);
  } on DioException catch (e) {
    log('DioException: ${e.message}');

    if (e.response != null) {
      return ApiError(
        code: e.response!.statusCode!,
        message: e.response!.statusMessage,
      );
    } else {
      return ApiError(
        code: -1,
        message: e.message,
      );
    }
  } on Exception catch (e) {
    log('Exception: ${e.toString()}');

    return ApiError(
      code: -1,
      message: e.toString(),
    );
  }
}
