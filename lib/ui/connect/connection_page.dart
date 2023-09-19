import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:kimai/data/models/screen_status.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/ui/common/custom_textfield.dart';
import 'package:kimai/ui/common/wide_fab.dart';
import 'package:kimai/ui/home/home_page.dart';
import 'package:kimai/utils/extensions.dart';
import 'package:lucide_icons/lucide_icons.dart';

import 'cubit/connection_cubit.dart';

class ConnectionPage extends StatefulWidget {
  const ConnectionPage({super.key});

  @override
  State<ConnectionPage> createState() => _ConnectionPageState();
}

class _ConnectionPageState extends State<ConnectionPage> {
  final _formKey = GlobalKey<FormState>();
  late TextEditingController _baseURLController;
  late TextEditingController _usernameController;
  late TextEditingController _apiKeyController;

  @override
  void initState() {
    super.initState();
    _baseURLController = TextEditingController();
    _usernameController = TextEditingController();
    _apiKeyController = TextEditingController();
  }

  @override
  void dispose() {
    _baseURLController.dispose();
    _usernameController.dispose();
    _apiKeyController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocConsumer<ConnectionCubit, ConnState>(
      bloc: getIt<ConnectionCubit>(),
      listenWhen: (pre, curr) => pre.status != curr.status,
      listener: (context, state) {
        if (state.status == ScreenStatus.success) {
          Fluttertoast.showToast(msg: 'Connection successful');
          context.navigator.pushAndRemoveUntil(
            HomePage().route(),
            (route) => false,
          );
        }

        if (state.status == ScreenStatus.failed) {
          Fluttertoast.showToast(msg: state.error ?? 'Connection failed');
        }
      },
      builder: (context, state) => Scaffold(
        body: CustomScrollView(
          physics: BouncingScrollPhysics(),
          slivers: [
            SliverAppBar.medium(
              titleSpacing: 0,
              title: Text('Connect instance'),
            ),
            SliverFillRemaining(
              hasScrollBody: false,
              child: Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: 20.0,
                  vertical: 12.0,
                ),
                child: Form(
                  key: _formKey,
                  child: Column(
                    children: [
                      CustomTextField(
                        controller: _baseURLController,
                        hintText: 'https://example.kimai.com',
                        labelText: 'Instance URL',
                        prefixIcon: Icon(LucideIcons.server),
                        onChanged: (val) =>
                            getIt<ConnectionCubit>().baseURLChanged(val),
                        validator: (value) {
                          if (value?.isEmpty == true) {
                            return 'Please enter a valid URL';
                          }

                          return null;
                        },
                      ),
                      const SizedBox(height: 24),
                      CustomTextField(
                        controller: _usernameController,
                        hintText: 'user1234',
                        labelText: 'Email / Username',
                        prefixIcon: Icon(LucideIcons.userCircle),
                        onChanged: (val) =>
                            getIt<ConnectionCubit>().usernameChanged(val),
                        validator: (value) {
                          if (value?.isEmpty == true) {
                            return 'Please enter a valid username';
                          }

                          return null;
                        },
                      ),
                      const SizedBox(height: 24),
                      CustomTextField(
                        controller: _apiKeyController,
                        hintText: 'abcd1234',
                        labelText: 'Api Token',
                        prefixIcon: Icon(LucideIcons.keyRound),
                        obscure: true,
                        onChanged: (val) =>
                            getIt<ConnectionCubit>().apiKeyChanged(val),
                        validator: (value) {
                          if (value?.isEmpty == true) {
                            return 'Please enter a valid key';
                          }

                          return null;
                        },
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ],
        ),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        floatingActionButton: WideFab(
          label: 'Connect',
          isLoading: state.status == ScreenStatus.loading,
          onPressed: () {
            if (_formKey.currentState?.validate() == true) {
              getIt<ConnectionCubit>().connect();
            }
          },
        ),
      ),
    );
  }
}
