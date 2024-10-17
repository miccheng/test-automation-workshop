import { mount } from '@vue/test-utils'
import TodoActions from '../src/components/TodoActions.vue'
import { describe, expect, it } from 'vitest'

describe('TodoActions.vue', () => {
  it('should hide clear completed button when no To Do completed', () => {
    const wrapper = mount(TodoActions, {props: { hasCompleted: false }})

    expect(wrapper.find('.clearCompletedBtn').exists()).toBe(false)
  })

  describe('when there are completed tasks', () => {
    it('should show clear completed button', () => {
      const wrapper = mount(TodoActions, {props: { hasCompleted: true }})
  
      expect(wrapper.get('.clearCompletedBtn').isVisible()).toBe(true)
    })

    it('emits clear-completed event when clicked on Clear Completed', async () => {
      const wrapper = mount(TodoActions, {props: { hasCompleted: true }})

      await wrapper.get('.clearCompletedBtn').trigger('click');
  
      expect(wrapper.emitted()['clear-completed']).toBeTruthy();
    })
  })
})