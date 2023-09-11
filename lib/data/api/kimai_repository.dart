import 'package:kimai/utils/api_result.dart';
import 'package:kimai/data/models/models.dart';

abstract class KimaiRepository {
  Future<ApiResult<Ping>> ping();

  Future<ApiResult<List<Activity>>> getActivities({
    int? projectId,
    List<int>? projects,
    int visible = 1,
    bool? globals,
    String? orderBy,
    String order = 'ASC',
    String? searchTerm,
  });

  Future<ApiResult<List<Activity>>> getProjects({
    int visible = 1,
  });

  Future<ApiResult<List<TimesheetActivity>>> getTimeSheets({
    String? orderBy,
    String? order,
  });

  Future<ApiResult<List<Customer>>> getCustomers({
    int visible = 1,
  });

  Future<ApiResult<TimesheetActivity>> createTimeSheet({
    required String begin,
    String? end,
    required int project,
    required int activity,
  });
}
