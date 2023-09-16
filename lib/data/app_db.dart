import 'package:injectable/injectable.dart';
import 'package:isar/isar.dart';

import 'schema/instance.dart';

@lazySingleton
class AppDatabase {
  final Isar _isar;

  AppDatabase({
    required Isar isar,
  }) : _isar = isar;

  Future<Instance?> getInstance({
    int id = 1,
  }) async {
    late Instance? instance;

    await _isar.txn(() async {
      final instances = await _isar.instances.where().findAll();
      instance = instances.firstOrNull;
    });

    return instance;
  }

  Future<void> saveInstance({
    required Instance instance,
  }) async {
    await _isar.writeTxn(() async {
      await _isar.instances.put(instance);
    });
  }
}
