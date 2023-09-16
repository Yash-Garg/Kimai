import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/api/api_paths.dart';
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
      final url = '${user.baseUrl}${ApiPaths.PING}';
      final headers = {
        'X-AUTH-USER': user.username,
        'X-AUTH-TOKEN': user.token,
      };

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
    try {
      final response = await _dio.get(
        ApiPaths.GET_ACTIVITIES,
        queryParameters: {
          if (projectId != null) 'project': projectId,
          if (projects != null) 'projects': projects,
          if (globals != null) 'globals': globals,
          if (orderBy != null) 'orderBy': orderBy,
          if (searchTerm != null) 'searchTerm': searchTerm,
          'order': order,
          'visible': visible,
        },
      );

      return Success(
        (response.data as List).map((e) => Activity.fromJson(e)).toList(),
      );
    } on Exception catch (e) {
      return Failure(e);
    }
  }

  @override
  Future<Result<List<Customer>>> getCustomers({
    int visible = 1,
  }) async {
    try {
      final response = await _dio.get(
        ApiPaths.GET_CUSTOMERS,
        queryParameters: {
          'visible': visible,
        },
      );

      return Success(
        (response.data as List).map((e) => Customer.fromJson(e)).toList(),
      );
    } on Exception catch (e) {
      return Failure(e);
    }
  }

  @override
  Future<Result<List<Activity>>> getProjects({
    int visible = 1,
  }) async {
    try {
      final response = await _dio.get(
        ApiPaths.GET_PROJECTS,
        queryParameters: {
          'visible': visible,
        },
      );

      return Success(
        (response.data as List).map((e) => Activity.fromJson(e)).toList(),
      );
    } on Exception catch (e) {
      return Failure(e);
    }
  }

  @override
  Future<Result<List<TimesheetActivity>>> getTimeSheets({
    String? orderBy,
    String? order,
  }) async {
    try {
      final response = await _dio.get(
        ApiPaths.GET_TIMESHEETS,
        queryParameters: {
          'orderBy': orderBy,
          'order': order,
        },
      );

      return Success(
        (response.data as List)
            .map((e) => TimesheetActivity.fromJson(e))
            .toList(),
      );
    } on Exception catch (e) {
      return Failure(e);
    }
  }
}
