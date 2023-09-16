import 'package:flutter/material.dart';
import 'package:kimai/ui/common/custom_textfield.dart';
import 'package:kimai/ui/common/wide_fab.dart';
import 'package:lucide_icons/lucide_icons.dart';

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
    return Scaffold(
      appBar: AppBar(
        title: Text('Connect instance'),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 12.0),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              CustomTextField(
                controller: _baseURLController,
                hintText: 'https://example.kimai.com',
                labelText: 'Instance URL',
                prefixIcon: Icon(LucideIcons.server),
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
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      floatingActionButton: WideFab(
        label: 'Connect',
        onPressed: () {},
      ),
    );
  }
}
