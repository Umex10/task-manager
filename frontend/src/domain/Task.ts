import { TaskPriority } from "./TaskPriority";
import { TaskStatus } from "./TaskStatus";

// The interface is permissive so builds don't fail; runtime code ensures required fields.
interface Task {
  id?: string;            // optional during creation, filled after backend response
  title: string;
  description?: string;
  dueDate?: Date | string;
  priority: TaskPriority;
  status?: TaskStatus;    // may be set default OPEN in UI if absent
}
export default Task;
