import 'package:kimai/data/models/auth_user.dart';
import 'package:kimai/utils/api_result.dart';
import 'package:kimai/data/models/models.dart';

abstract class KimaiRepository {
  Future<Result<Ping>> ping(AuthUser user);

  Future<Result<List<Activity>>> getActivities({
    int? projectId,
    List<int>? projects,
    int visible = 1,
    bool? globals,
    String? orderBy,
    String order = 'ASC',
    String? searchTerm,
  });

  Future<Result<List<Activity>>> getProjects({
    int visible = 1,
  });

  Future<Result<List<TimesheetActivity>>> getTimeSheets({
    String? orderBy,
    String? order,
  });

  Future<Result<List<Customer>>> getCustomers({
    int visible = 1,
  });

  Future<Result<TimesheetActivity>> createTimeSheet({
    required String begin,
    String? end,
    required int project,
    required int activity,
  });
}
