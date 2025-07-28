# Copilot Instructions for codecrafters-shell-java

This project implements a POSIX-like shell in Java for the CodeCrafters "Build Your Own Shell" challenge.

## Project Structure
- **Entry Point:** `src/main/java/Main.java` contains the main REPL loop and all command handling logic.
- **Build/Run:**
  - Use `./your_program.sh` to build and run the shell (wraps Maven and Java execution).
  - Main build artifact: `target/codecrafters-shell.jar`.
- **No custom test framework**: All validation is done via CodeCrafters' remote runner after `git push`.

## Command Handling Patterns
- **Builtins:**
  - `exit 0`, `echo`, `type`, `pwd`, and `cd` are handled directly in `Main.java`.
  - Example: `echo` prints the substring after `echo `, `pwd` prints the current directory.
- **External Commands:**
  - Any other input is split by whitespace and executed via `ProcessBuilder`.
  - Both stdout and stderr are captured and printed.
- **Error Handling:**
  - Unknown commands print `<input>: command not found`.
  - Exceptions are caught and printed to stderr.

## Implementation Conventions
- **No subdirectories for commands:** All logic is in `Main.java`.
- **No external dependencies** beyond standard Java and Maven.
- **No config files** for shell behavior; all logic is hardcoded.
- **String parsing:** Uses `split("\\s+")` for argument parsing; does not handle quotes or escapes.

## Key Files
- `src/main/java/Main.java`: All shell logic.
- `your_program.sh`: Entrypoint script for running the shell.
- `README.md`: Challenge instructions and workflow.

## Example Workflow
- Edit `Main.java` to add or modify command handling.
- Run `./your_program.sh` to test locally.
- Commit and push to trigger CodeCrafters tests.

## Tips for AI Agents
- Focus on modifying `Main.java` for all shell features.
- When adding new builtins, follow the existing `else if` pattern.
- For external commands, use `ProcessBuilder` as shown.
- Do not introduce new files or directories unless required by the challenge.
- Keep logic simple and POSIX-like; avoid advanced shell features unless specified.

---
If any conventions or workflows are unclear, please request clarification or examples from the user.
