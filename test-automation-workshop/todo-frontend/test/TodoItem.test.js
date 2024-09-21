import { mount } from '@vue/test-utils'
import TodoItem from '../src/components/TodoItem.vue'

describe('TodoItem.vue', () => {
  const todo = { id: 1, task: 'Test Task', completed: false };

  it('renders the task text', () => {
    const wrapper = mount(TodoItem, {
      props: { todo }
    });
    expect(wrapper.text()).toContain('Test Task');
  });

  it('emits toggle-complete event on checkbox change', async () => {
    const wrapper = mount(TodoItem, {
      props: { todo }
    });
    await wrapper.find('input[type="checkbox"]').trigger('change');
    expect(wrapper.emitted()['toggle-complete']).toBeTruthy();
    expect(wrapper.emitted()['toggle-complete'][0]).toEqual([{ id: 1, completed: true }]);
  });

  it('emits edit-todo event on edit button click', async () => {
    const wrapper = mount(TodoItem, {
      props: { todo }
    });
    await wrapper.find('button').trigger('click');
    expect(wrapper.emitted()['edit-todo']).toBeTruthy();
    expect(wrapper.emitted()['edit-todo'][0]).toEqual([todo]);
  });

  it('emits delete-todo event on delete button click', async () => {
    const wrapper = mount(TodoItem, {
      props: { todo }
    });
    const deleteButton = wrapper.findAll('button')[1];
    await deleteButton.trigger('click');
    expect(wrapper.emitted()['delete-todo']).toBeTruthy();
    expect(wrapper.emitted()['delete-todo'][0]).toEqual([todo.id]);
  });
});
