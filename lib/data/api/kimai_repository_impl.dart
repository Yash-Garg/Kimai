import 'dart:developer';

import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/api/kimai_repository.dart';
import 'package:kimai/data/models/auth_user.dart';
import 'package:kimai/data/models/models.dart';
import 'package:kimai/utils/api_result.dart';

@LazySingleton(as: KimaiRepository)
class KimaiRepositoryImpl implements KimaiRepository {
  final Dio _dio;

  KimaiRepositoryImpl({
    required Dio dio,
  }) : _dio = dio;

  @override
  Future<Result<Ping>> ping(AuthUser user) async {
    try {
      final url = '${user.baseUrl}/api/ping';
      final headers = {
        'X-AUTH-USER': user.username,
        'X-AUTH-TOKEN': user.token,
      };

      log('pinging url: $url | headers: $headers');

      final response = await _dio.get(
        url,
        options: Options(headers: headers),
      );

      return Success(Ping.fromJson(response.data));
    } on Exception catch (e) {
      return Failure(e);
    }
  }

  @override
  Future<Result<TimesheetActivity>> createTimeSheet({
    required String begin,
    String? end,
    required int project,
    required int activity,
  }) async {
    // TODO: implement createTimeSheet
    throw UnimplementedError();
  }

  @override
  Future<Result<List<Activity>>> getActivities({
    int? projectId,
    List<int>? projects,
    int visible = 1,
    bool? globals,
    String? orderBy,
    String order = 'ASC',
    String? searchTerm,
  }) async {
    // TODO: implement getActivities
    throw UnimplementedError();
  }

  @override
  Future<Result<List<Customer>>> getCustomers({
    int visible = 1,
  }) async {
    // TODO: implement getCustomers
    throw UnimplementedError();
  }

  @override
  Future<Result<List<Activity>>> getProjects({
    int visible = 1,
  }) async {
    // TODO: implement getProjects
    throw UnimplementedError();
  }

  @override
  Future<Result<List<TimesheetActivity>>> getTimeSheets({
    String? orderBy,
    String? order,
  }) async {
    // TODO: implement getTimeSheets
    throw UnimplementedError();
  }
}
