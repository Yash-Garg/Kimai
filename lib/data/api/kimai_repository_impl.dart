import 'dart:developer';

import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/api/kimai_repository.dart';
import 'package:kimai/utils/api_result.dart';
import 'package:kimai/data/models/models.dart';

@LazySingleton(as: KimaiRepository)
class KimaiRepositoryImpl implements KimaiRepository {
  final Dio _dio;

  KimaiRepositoryImpl({
    required Dio dio,
  }) : _dio = dio;

  @override
  Future<ApiResult<Ping>> ping() => handleApi(() {
        log('calling ping');
        return _dio.get('/api/ping');
      });

  @override
  Future<ApiResult<TimesheetActivity>> createTimeSheet({
    required String begin,
    String? end,
    required int project,
    required int activity,
  }) {
    // TODO: implement createTimeSheet
    throw UnimplementedError();
  }

  @override
  Future<ApiResult<List<Activity>>> getActivities({
    int? projectId,
    List<int>? projects,
    int visible = 1,
    bool? globals,
    String? orderBy,
    String order = 'ASC',
    String? searchTerm,
  }) {
    // TODO: implement getActivities
    throw UnimplementedError();
  }

  @override
  Future<ApiResult<List<Customer>>> getCustomers({
    int visible = 1,
  }) {
    // TODO: implement getCustomers
    throw UnimplementedError();
  }

  @override
  Future<ApiResult<List<Activity>>> getProjects({
    int visible = 1,
  }) {
    // TODO: implement getProjects
    throw UnimplementedError();
  }

  @override
  Future<ApiResult<List<TimesheetActivity>>> getTimeSheets({
    String? orderBy,
    String? order,
  }) {
    // TODO: implement getTimeSheets
    throw UnimplementedError();
  }
}
