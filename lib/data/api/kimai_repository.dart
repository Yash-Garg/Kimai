import 'package:fpdart/fpdart.dart';
import 'package:kimai/data/models/auth_user.dart';

import 'package:kimai/data/models/models.dart';

abstract class KimaiRepository {
  Future<Either<Ping, Exception>> ping(AuthUser user);

  Future<Either<List<Activity>, Exception>> getActivities({
    int? projectId,
    List<int>? projects,
    int visible = 1,
    bool? globals,
    String? orderBy,
    String order = 'ASC',
    String? searchTerm,
  });

  Future<Either<List<Activity>, Exception>> getProjects({
    int visible = 1,
  });

  Future<Either<List<TimesheetActivity>, Exception>> getTimeSheets({
    String? orderBy,
    String? order,
  });

  Future<Either<List<Customer>, Exception>> getCustomers({
    int visible = 1,
  });

  Future<Either<TimesheetActivity, Exception>> createTimeSheet({
    required String begin,
    String? end,
    required int project,
    required int activity,
  });

  Future<Either<UserEntity, Exception>> getMyUser();
}
