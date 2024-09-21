import { mount } from '@vue/test-utils'
import TodoForm from '../src/components/TodoForm.vue'

describe('TodoForm.vue', () => {
  it('renders an empty form for adding a new task', () => {
    const wrapper = mount(TodoForm);
    expect(wrapper.find('input').element.value).toBe('');
    expect(wrapper.find('button[type="submit"]').text()).toBe('Add Task');
  });

  it('updates form fields when in edit mode', async () => {
    const taskToEdit = { id: 1, task: 'Test Task', completed: false };
    const wrapper = mount(TodoForm, {
      props: { editMode: true, taskToEdit }
    });

    expect(wrapper.find('input').element.value).toBe('Test Task');
    expect(wrapper.find('button[type="submit"]').text()).toBe('Update Task');
  });

  it('emits add-task event when submitting new task', async () => {
    const wrapper = mount(TodoForm);
    const input = wrapper.find('input');
    await input.setValue('New Task');
    await wrapper.find('form').trigger('submit.prevent');

    expect(wrapper.emitted()['add-task']).toBeTruthy();
    expect(wrapper.emitted()['add-task'][0]).toEqual([{ task: 'New Task', completed: false }]);
  });

  it('emits update-task event when editing a task', async () => {
    const taskToEdit = { id: 1, task: 'Test Task', completed: false };
    const wrapper = mount(TodoForm, {
      props: { editMode: true, taskToEdit }
    });

    const input = wrapper.find('input');
    await input.setValue('Updated Task');
    await wrapper.find('form').trigger('submit.prevent');

    expect(wrapper.emitted()['update-task']).toBeTruthy();
    expect(wrapper.emitted()['update-task'][0]).toEqual([{ id: 1, task: 'Updated Task', completed: false }]);
  });

  it('emits cancel-edit event when cancel button is clicked', async () => {
    const wrapper = mount(TodoForm, {
      props: { editMode: true }
    });
    await wrapper.find('button[type="button"]').trigger('click');
    expect(wrapper.emitted()['cancel-edit']).toBeTruthy();
  });
});
